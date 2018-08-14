/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageFormatsMySuffixDetailComponent } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix-detail.component';
import { EntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageFormatsMySuffix Management Detail Component', () => {
        let comp: EntityPageFormatsMySuffixDetailComponent;
        let fixture: ComponentFixture<EntityPageFormatsMySuffixDetailComponent>;
        const route = ({ data: of({ entityPageFormats: new EntityPageFormatsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageFormatsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EntityPageFormatsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityPageFormatsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.entityPageFormats).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
